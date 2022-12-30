use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;
use std::collections::LinkedList;


fn main() {
    let stack_number = 9;
    let mut stack_input = true;
    // create array of linked list
    let mut stack = Vec::new();
    for _ in 0..stack_number{
        stack.push(LinkedList::new());
    }

    if let Ok(lines) = read_lines("./input") {
        for line in lines {
            if let Ok(txt) = line{
                if txt == "" {
                    stack_input = false;
                    continue;
                }

                if stack_input{
                    for i in 0..stack_number{
                        let c = txt.chars().nth(1+4*i).unwrap();
                        // if c is number or space then continue
                        if c == ' ' || c.is_numeric(){
                            continue;
                        }
                        stack[i].push_front(c);
                    }
                }
                else{
                    let split = txt.split_whitespace();
                    // get 2nd value and convert to number
                    let n = split.clone().nth(1).unwrap().parse::<usize>().unwrap();
                    let from = split.clone().nth(3).unwrap().parse::<usize>().unwrap();
                    let to = split.clone().nth(5).unwrap().parse::<usize>().unwrap();
                    for _ in 0..n{
                        let c = stack[from-1].pop_back().unwrap();
                        stack[to-1].push_back(c);
                    }
                }
            }
        }

        // print all elements in each stack
        // for i in 0..stack_number{
        //     let mut s = String::new();
        //     for c in stack[i].iter(){
        //         s.push(*c);
        //     }
        //     println!("{}", s);
        //     println!("{}", s.len());
        // }
    }

    // print last element in each stack
    for i in 0..stack_number{
        print!("{}", stack[i].pop_back().unwrap());
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}