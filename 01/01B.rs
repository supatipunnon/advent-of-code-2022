use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        let mut sum: i32 = 0;
        let mut max: [i32; 3] = [0, 0, 0];
        for line in lines {
            // sum each line until a blank line is found and then reset sum to 0
            if let Ok(ip) = line {
                if ip == "" {
                    if sum >= max[0] {
                        max[2] = max[1];
                        max[1] = max[0];
                        max[0] = sum;
                    }
                    sum = 0;
                } else {
                    sum += ip.parse::<i32>().unwrap();
                }
            }
        }
        //print sum of the 3 largest numbers
        println!("Max: {}", max[0] + max[1] + max[2]);
    }
}

fn read_lines<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}