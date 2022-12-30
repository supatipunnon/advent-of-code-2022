use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        let mut sum: i32 = 0;
        for line in lines {
            if let Ok(txt) = line {
                // split line into 2 parts with half the length of the line
                let (a, b) = txt.split_at(txt.len() / 2);
                // find the character that appears in both parts
                for i in a.chars() {
                    if b.contains(i) {
                        let ascii = i as u8;
                        // check if the character is uppercase or lowercase
                        if i.is_uppercase() {
                            sum += (ascii - 64) as i32 + 26;
                        } else {
                            sum += (ascii - 96) as i32;
                        }
                        break;
                    }
                }
            }
        }
        println!("Sum: {}", sum);
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}