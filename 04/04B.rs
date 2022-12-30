use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        let mut sum = 0;
        for line in lines {
            if let Ok(txt) = line {
                // split text with ","
                let mut split = txt.split(",");
                // get first value
                let mut first = split.next().unwrap().split("-");
                let  a = first.next().unwrap().parse::<i32>().unwrap();
                let  b = first.next().unwrap().parse::<i32>().unwrap();
                // get second value
                let mut second = split.next().unwrap().split("-");
                let  x = second.next().unwrap().parse::<i32>().unwrap();
                let  y = second.next().unwrap().parse::<i32>().unwrap();

                if(a <= x && b >= y)||(a >=x && b <= y) || (a <= y && a >= x)||(b >=x && b <= y)
                {
                    sum += 1;
                }                
            }
        }
        println!("sum: {}", sum);
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}