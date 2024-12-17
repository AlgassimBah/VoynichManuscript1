# Voynich Reader

**Voynich Reader** is a Java-based tool designed to assist in deciphering text from the Voynich Manuscript. It performs basic frequency analysis and decryption using a predefined cipher map, allowing users to analyze and interpret text from a provided manuscript file.

## Features

1. **Text Cleaning and Splitting**: Processes the ciphertext by removing unwanted characters and splitting the text into words.
2. **Frequency Analysis**: Analyzes character and word frequency to identify patterns in the text.
3. **Decryption**: Deciphers the text using a predefined character-to-character cipher map.
4. **Output**: Provides a decrypted version of the text along with frequency analysis results.

## How It Works

1. **Input**: The program reads ciphertext from a file (`capture/manuscript.txt`).
2. **Analysis**:
   - Cleans the text.
   - Performs frequency analysis on characters and words.
3. **Output**: Displays the frequency analysis and the decrypted text in the console.

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or later.
- **Java IDE**: IntelliJ IDEA (or any Java IDE of your choice).
- The ciphertext file (`capture/manuscript.txt`) should be available in the specified directory.

## Installation and Usage

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA or your preferred Java IDE.
3. Ensure the ciphertext file (`capture/manuscript.txt`) is present in the `capture` folder relative to the project root.
4. Run the `VoynichReader` class.
5. View the frequency analysis and decrypted text output in the console.

## File Structure

- **VoynichReader.java**: The main program file.
- **capture/manuscript.txt**: The input file containing ciphertext.

## Cipher Map

The decryption process uses the following predefined cipher map:

| Ciphered Character | Deciphered Character |
|--------------------|-----------------------|
| `o`                | `a`                  |
| `e`                | `e`                  |
| `c`                | `t`                  |
| `h`                | `o`                  |
| `y`                | `n`                  |
| `p`                | `r`                  |
| `.`                | (space)              |

## Supported Languages and Multilingual Features

While the program is written in **Java**, it supports translations into multiple languages based on dictionary tables. This functionality allows for a more comprehensive analysis by cross-referencing decrypted text with possible word matches in different linguistic contexts.

### Programming Language

- **Java**: The entire program is developed in Java, utilizing its robust file handling, data processing, and collection framework capabilities.

### Language Dictionary Support

- **English** (default): Matches decrypted text with English word patterns.
- **Potential Multilingual Extensions**:
  - Other languages can be added by extending the dictionary table in the program. For example:
    - `ata` → ["father" (English), "data" (Latin)]
    - `che` → ["what" (Italian), "who" (Spanish)]
  - Add these mappings to the dictionary logic in the program.

