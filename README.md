# Sudokoo-
A sudoku project written in java.

Student Name: Dylan Aryeetey

Description:
This project is a Java program that amkes a fully solved Sudoku board. The goal was to create code that consistently produces valid Sudoku boards while making each one look different. Instead of relying on random guessing, the code uses a structured pattern of numbers and then scrambles it.

How to Run the Program:
To run the code, open the file in any Java IDE or compile it using the command line. Run the Sudoku class, and the code will automatically generate and print a completed Sudoku board in the console.

How the Sudoku Board is Generated:
The code starts by creating a base row with the numbers 1 through 9. It then builds the rest of the board by rotating this row in a specific pattern that guarantees all rows, columns, and 3×3 sections are valid. After that, it scrambles rows and columns within certain groups to make the board look random while still keeping it correct. Finally, it checks the board to make sure it follows all Sudoku rules.

Files Included:
Sudoku.java– Contains all the code for generating, validating, and printing the Sudoku board
