use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        let mut sum: i32 = 0;
        for line in lines {
            if let Ok(txt) = line {
                // get first charachter of line
                let abc = txt.chars().nth(0).unwrap();
                let xyz = txt.chars().nth(2).unwrap();
                match xyz{
                    'X' => {
                        sum += 1;
                        match abc{
                            'A' => sum += 3,
                            'C' => sum += 6,
                            _ => sum += 0,
                        }
                    },
                    'Y' => {
                        sum += 2;
                        match abc{
                            'B' => sum += 3,
                            'A' => sum += 6,
                            _ => sum += 0,
                        }
                    },
                    'Z' => {
                        sum += 3;
                        match abc{
                            'C' => sum += 3,
                            'B' => sum += 6,
                            _ => sum += 0,
                        }
                    },
                    _ => sum += 0,
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