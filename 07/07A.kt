import java.io.File
import java.util.StringTokenizer
import java.util.ArrayList

class Node(var value: Int, var parent: Node? = null, var children: ArrayList<Node> = ArrayList())
{
    fun addChild(node: Node)
    {
        children.add(node)
    }

    // override toString to print the tree
    override fun toString(): String
    {
        var str = ""
        str += value
        if (children.size > 0)
        {
            str += " {"
            for (child in children)
            {
                str += child.toString()
            }
            str += "}"
        }
        return str
    }
}

fun main()
{
    val file = File("input");
    var root: Node? = null;
    //use string tokenizer to read the file
    val st = StringTokenizer(file.readText());
    while(st.hasMoreTokens())
    {
        val token = st.nextToken()
        when(token){
            "cd" -> {
                root = cd(st);
            }
            else -> {continue;}
        }
    }
    
    println(dfs(root!!));
    // println(root);
    
}

fun cd(st: StringTokenizer): Node
{
    val node = Node(0);
    while(st.hasMoreTokens())
    {
        val token = st.nextToken()
        // if token is a number
        if (token.toIntOrNull() != null)
        {
            node.value += token.toInt()
        }
        else
        {
            when(token){
                "cd" -> {
                    if (st.nextToken() == "..")
                    {
                        return node;
                    }
                    val child = cd(st)
                    node.addChild(child)
                    child.parent = node;
                }
                else -> {continue;}
            }
        }
    }
    return node;
}

// dfs function to add values form children to parent
fun dfs(node: Node): Int
{
    var num = 0;
    for (child in node.children)
    {
        num+=dfs(child)
        node.value += child.value
    }
    if (node.value < 100000){
        num+=node.value;
    }
    return num;
}