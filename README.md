# _Review Site_

#### _Epicodus: Web Applications With Java, Review Site_

#### By _**Caleb Stevenson &amp; Adam Gorbahn**_

## Description

This program stores words in a personal dictionary and allows you to add multiple definitions to a word.

## Specs

| BEHAVIOR                                               | INPUT                                 | OUTPUT                                                                                            |
|--------------------------------------------------------|---------------------------------------|---------------------------------------------------------------------------------------------------|
| Program creates new platform entry.                    | PS4                                   | PS4                                                                                               |
| Program creates new game entry of a specific platform. | Bloodborne                            | Bloodborne (PS4)                                                                                  |
| Program creates reviews of game entries.               | Super hard!                           | Reviews of Bloodborne (PS4) <ol> <li>Super hard!</li> </ol>                                       |
| Program creates multiple reviews of game entries.      | Easy game.                            |  Reviews of Bloodborne (PS4) <ol> <li>Super hard!</li> <li>Easy game.</li> </ol>                  |
| Program updates a review of a game entry.              | Never mind, super difficult.          | Reviews of Bloodborne (PS4) <ol> <li>Super hard!</li> <li>Never mind, super difficult.</li> </ol> |
| Program deletes a review of a game entry.              | DELETE "Never mind, super difficult." | Reviewsof Bloodborne (PS4) <ol>  <li>Super hard!</li> </ol>                                       |

## Setup/Installation Requirements

* Clone this repository to your desktop: `$ git clone https://github.com/CGrahamS/review`

* In the terminal, navigate to /dictionary by typing `$ cd Desktop/dictionary`

* Run the program by typing in the terminal: `$ gradle run`

## Known Bugs

_None_

## Support and contact details

Caleb Stevenson: _cgrahamstevenson@gmail.com_
Adam Gorbahn: _adamgorbahn@hotmail.com_

## Technologies Used

_Java,
Spark,
Velocity_

### License

This webpage is licensed under the GPL license.

Copyright &copy; 2016 **_Caleb Stevenson_**
