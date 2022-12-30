package `08A`
import java.io.File

fun main()
{
    val file = File("input");
    // read string from file and split it into lines
    val input = file.readText()
    val matrix = splitIntoMatrix(input);
    var maxRow = Array(matrix.size) {0}
    var maxCol = Array(matrix[0].size) {0}
    val visited = Array(matrix.size) {Array(matrix[0].size) {false} }
    
    var count = 0;

    // check if tree see from top or left
    for (r in 0 until matrix.size)
    {
        for (c in 0 until matrix[0].size)
        {
            if (r == 0 || c == 0){
                count++;
                visited[r][c] = true;
                if (r == 0){
                    maxCol[c] = matrix[0][c];
                }else
                {
                    maxRow[r] = matrix[r][0];
                }
            }else{
                if (matrix[r][c] > maxRow[r] || matrix[r][c] > maxCol[c]){
                    count++;
                    visited[r][c] = true;
                    if (matrix[r][c] > maxRow[r]){
                        maxRow[r] = matrix[r][c];
                    }
                    if (matrix[r][c] > maxCol[c]){
                        maxCol[c] = matrix[r][c];
                    }
                }
            }
        }
    }

    maxRow = Array(matrix.size) {0}
    maxCol = Array(matrix[0].size) {0}

    // check if tree see from bottom or right
    for (r in matrix.size - 1 downTo 0)
    {
        for (c in matrix[0].size - 1 downTo 0)
        {
            if (r == matrix.size - 1 || c == matrix[0].size - 1){
                if (!visited[r][c]){
                    count++;
                }
                if (r == matrix.size - 1){
                    maxCol[c] = matrix[matrix.size - 1][c];
                }else
                {
                    maxRow[r] = matrix[r][matrix[0].size - 1];
                }
            }else{
                if (matrix[r][c] > maxRow[r] || matrix[r][c] > maxCol[c]){
                    if (!visited[r][c]){
                        count++;
                    }
                    if (matrix[r][c] > maxRow[r]){
                        maxRow[r] = matrix[r][c];
                    }
                    if (matrix[r][c] > maxCol[c]){
                        maxCol[c] = matrix[r][c];
                    }
                }
            }
        }
    }

    println(count);
}


// code form chat OpenAI
fun splitIntoMatrix(input: String): Array<Array<Int>> {
    // Split the input string into individual lines
    val lines = input.split("\n").map { it.trim() }.filter { it.isNotEmpty() }

    // Use the map function to parse each line as an array of integers
    val matrix = lines.map { line ->
        line.map { ch -> ch.toString().toInt() }.toTypedArray()
    }.toTypedArray()

    return matrix
}