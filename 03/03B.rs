use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

fn main() {
    
    if let Ok(lines) = read_lines("./input") {
        let mut char_array: [i32; 52] = [0; 52];
        let mut ascii_value = 0;
        let mut sum = 0;
        //for each line and the line number
        for (line_number, line) in lines.enumerate() {
            if let Ok(line) = line {
                //for each character in the line
                for (_char_index, character) in line.chars().enumerate() {
                    if character.is_lowercase() {
                        ascii_value = character as usize - 97;
                    } else if character.is_uppercase() {
                        ascii_value = character as usize - 65 +26;
                    }
                    if char_array[ascii_value] < 0 {
                        continue;
                    }

                    char_array[ascii_value] += 1;
                    char_array[ascii_value] *= -1;
                }
                for index in 0..52 {
                    if char_array[index] < 0 {
                        char_array[index] *= -1;
                    }
                }

                if line_number%3 == 2 {
                    for (index, _count) in char_array.iter().enumerate() {
                        if char_array[index] == 3 as i32 {
                            sum += index+1;
                        }
                    }
                    char_array = [0; 52];
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