// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract BankAccount {
    address public owner;
    uint256 private balance;

    // Event declarations
    event Deposit(address indexed accountOwner, uint256 amount);
    event Withdraw(address indexed accountOwner, uint256 amount);

    // Constructor marked as payable
    constructor() payable {
        owner = msg.sender; // Set the contract creator as the owner
        balance = msg.value; // Initialize balance with the sent Ether
    }

    // Function to deposit money
    function deposit() external payable {
        require(msg.value > 0, "Deposit amount must be greater than zero.");
        balance += msg.value; // Increase the balance by the deposit amount
        emit Deposit(msg.sender, msg.value); // Emit deposit event
    }

    // Function to withdraw money
    function withdraw(uint256 amount) external {
        require(msg.sender == owner, "Only the owner can withdraw funds.");
        require(amount <= balance, "Insufficient balance.");
        
        balance -= amount; // Decrease the balance
        payable(owner).transfer(amount); // Transfer the amount to the owner
        emit Withdraw(msg.sender, amount); // Emit withdraw event
    }

    // Function to get the balance
    function getBalance() external view returns (uint256) {
        return balance; // Return the current balance
    }
}
