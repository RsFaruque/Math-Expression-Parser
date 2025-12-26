# Math-Expression-Parser
A parser for parsing mathematical expressions passed as a string. Uses shunting-yard algorithm to convert infix notation to postfix.

```bash
# mkdir bin
# javac -d bin src/stack_parser/*.java src/Main.java
# java -cp bin Main

10*(8+2)
Answer: 100
#------------
10*8+2
Answer: 82
#------------
10/2+6
Answer: 11
```