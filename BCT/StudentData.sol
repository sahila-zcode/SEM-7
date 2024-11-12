// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract StudentData {
    
    struct Student {
        string name;
        uint256 age;
        uint256 id;
    }

    // Array to store students
    Student[] public students;

    // Receive function to accept plain Ether transfers
    receive() external payable {
        // Logic for receiving Ether can be added here if needed
    }

    // Fallback function to accept ether with data
    fallback() external payable {
        // This function can accept ether with accompanying data
    }

    // Function to add a new student
    function addStudent(string memory _name, uint256 _age, uint256 _id) public {
        Student memory newStudent = Student({
            name: _name,
            age: _age,
            id: _id
        });
        students.push(newStudent); // Add the new student to the array
    }

    // Function to get student details by index
    function getStudent(uint256 index) public view returns (string memory, uint256, uint256) {
        require(index < students.length, "Student does not exist.");
        Student memory student = students[index];
        return (student.name, student.age, student.id);
    }

    // Function to get the total number of students
    function getStudentCount() public view returns (uint256) {
        return students.length;
    }
}
