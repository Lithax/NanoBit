# NanoBit Assembler

An official assembler for the **NanoBit CPU** architecture, designed and tested using [Sebastian Lague's Digital Logic Sim](https://github.com/SebLague/Digital-Logic-Sim). This project provides an easy way to convert assembly code into binary instructions that the NanoBit CPU can execute.

## Overview

The NanoBit CPU is a simple, custom-designed CPU architecture implemented in a virtual circuit environment. It supports basic operations like arithmetic, memory storage, and output. This assembler translates human-readable assembly code into machine code for execution.

---

## Features

- **Supported Instructions**:
  - `sum`: Adds two values.  
  - `sub`: Subtracts one value from another.  
  - `str`: Stores a value in a register.  
  - `out`: Outputs the contents of a register.  

- **Registers**:
  - `eax`: General-purpose register (`00`).
  - `edx`: General-purpose register (`01`).

- **Instruction Encoding**:
  - Instructions are represented as 3-bit binary codes.
  - Registers are represented as 2-bit binary codes.

---

## Usage

### Prerequisites

- Java Development Kit (JDK) installed.
- A text editor to create `.asm` files.
- Digital Logic Sim to test CPU execution (optional).

### Commands

1. **Assembling Code**  
   Assemble an assembly file (`input.asm`) into a binary file (`output.bin`):  
   ```
   java Asm_NanoBit -o input.asm output.bin
```
Output to Console
Assemble an assembly file and print the binary output:
```
java Asm_NanoBit -o input.asm -p
```
Help Command
Display available commands:
```
java Asm_NanoBit help
```
Example Assembly Code
asm
```
out edx
sub edx
sub eax
str eax
```
Output Binary Code
```
011 01
001 01
001 00
010 00
```
NanoBit CPU Architecture
The NanoBit CPU is a minimalistic 2-register CPU with the following characteristics:

Instruction Set:

sum: Adds values stored in eax and edx and stores the result.
sub: Subtracts the value in edx from eax and stores the result.
str: Stores the immediate value in a register.
out: Outputs the contents of a register.
Registers:

eax: 8-bit general-purpose accumulator.
edx: 8-bit general-purpose data register.
Control Signals:

Control signals are wired to perform basic fetch-decode-execute cycles.
Clock:

Single clock pulse per instruction cycle.
This CPU was implemented using Digital Logic Sim, and the assembler has been designed to provide an efficient interface for programming it.
