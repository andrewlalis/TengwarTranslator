# TengwarTranslator
![icon.png](/src/main/resources/icon.png "TengwarTranslator Icon")

### About the Program

User interface to translate english text into elvish tengwar script. This program takes english text, and using special rules, converts it into the tengwar script. Note, however, that Tengwar is not its own language, but just a way of representing language, so it is called a script.

### User Interface

Java's Built-in *Swing* library is used to provide the cross-platform user interface. I try to keep as few buttons and items on the screen as possible, so that only the basic functions are present, while still providing quite rich features. Essentially, however, every action *must* have a way to activate it via the user interface, so that there are no hidden features accessible by keypresses only.

### Translation

Since Tengwar is inherently a phonetic script, converting english to Tengwar would be almost impossible, and would require extremely advanced software to detect how individual words are pronounced. To avoid this, I created my [own Tengwar mode](/EnglishOneToOneTengwarV2-1.pdf "English to Tengwar") which can be used to transcribe to and from English in a way that no information is lost in the transcription, as would be the case with normal Tengwar. I do this by including characters to represent every English letter, while still obeying the rules concerning placement of vowel diacritics and compound characters. The characters are shown below.

![Tengwar](https://puu.sh/vtyfi/598ad704e1.png "Tengwar Characters")

To translate from Tengwar back to English is much simpler, but because Tengwar characters do use capitalization, the resulting string of text will be in only lower-case letters.

### To-Do List

* Implement s-curls.
* Add more text editing options, like color, italics, bold, indentation, and so on...
