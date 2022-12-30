use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        // Consumes the iterator, returns an (Optional) String
        let (mut max, mut sum): (i32, i32) = (0, 0);
        for line in lines {
            // sum each line until a blank line is found and then reset sum to 0
            if let Ok(ip) = line {
                if ip == "" {
                    if sum > max {
                        max = sum;
                    }
                    sum = 0;
                } else {
                    sum += ip.parse::<i32>().unwrap();
                }
            }
        }

        println!("Max: {}", max);
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}