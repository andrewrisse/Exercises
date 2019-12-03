// PART 1

// Create a constructor function for a Person, each person should have a firstName, lastName, favoriteColor and favoriteNumber. Your function MUST be named Person. 
// Write a method called multiplyFavoriteNumber that takes in a number and returns the product of the number and the object created from the Person functions' favorite number.


function Person(firstName, lastName, favoriteColor, favoriteNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.favoriteColor = favoriteColor;
    this.favoriteNumber = favoriteNumber;
    this.family = [];
    this.multiplyFavoriteNumber = function (num) {
        return this.favoriteNumber * num;
    }
}

// PART 2
// Given the following code - refactor the Child function to remove all the duplication from the Parent function. You should be able to remove 4 lines of code in the Child function and replace it with 1 single line.

function Parent(firstName, lastName, favoriteColor, favoriteFood) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.favoriteColor = favoriteColor;
    this.favoriteFood = favoriteFood;
}

function Child(firstName, lastName, favoriteColor, favoriteFood) {
    // this.firstName = firstName;
    // this.lastName = lastName;
    // this.favoriteColor = favoriteColor;
    // this.favoriteFood = favoriteFood;
    Parent.apply(this, arguments);
}



/* 2 - Add a function on the Person.prototype called fullName that returns the firstName and lastName property of an object created by the Person constructor concatenated together.
    
Examples:    
    var person = new Person("Elie", "Schoppik", "purple", 34)
    person.fullName() // "Elie Schoppik"

*/
Person.prototype.fullName = function () {
    return this.firstName + " " + (this.lastName);
}

// 3 -  Add a property on the object created from the Person function called family which is an empty array. This will involve you going back and adding an additional line of code to your Person constructor you previously created in exercise 1.

/* 4 - Add a function on the Person.prototype called addToFamily which adds an object constructed from the Person constructor to the family array. To make sure that the object you are adding is an object construced from the Person constructor (HINT - take a look at the instanceof keyword). Make sure that your family array does not include duplicates! This method should return the length of the family array.


Examples: 
    
    var person = new Person("Elie", "Schoppik", "purple", 34)
    var anotherPerson = new Person()
    person.addToFamily(anotherPerson); // 1
    person.addToFamily(anotherPerson); // 1
    person.family.length // 1
    
    person.addToFamily("test"); // 1
    person.addToFamily({}); // 1
    person.addToFamily([]); // 1
    person.addToFamily(false); // 1
    person.family.length // 1
*/

Person.prototype.addToFamily = function (member) {
    if (!this.family.includes(member) && member instanceof Person) {
        this.family.push(member);
    }
    return this.family.length;
}

// PART II 

// 1 - Implement your own version of Array.prototype.map. The function should accept a callback and return a new array with the result of the callback for each value in the array. 
Array.prototype.map = function (callback) {
    var newArr = [];
    for (var i = 0; i < this.length; i++) {
        newArr.push(callback(this[i], i, this));
    }
    return newArr;
}
/* 2 - Implement a function called reverse that reverses a string and place it on the String.prototype

Examples:
    "test".reverse() // "tset"
    "tacocat".reverse() // "tacocat"
*/

String.prototype.reverse = function () {
    var newStr = "";
    for (var i = this.length - 1; i >= 0; i--) {
        newStr += this[i];
    }
    return newStr;

}

// 1 - Create a constructor function for a Vehicle. Each vehicle should have a make, model and year property.
function Vehicle(make, model, year) {
    this.make = make;
    this.model = model;
    this.year = year;
}
// 2 - Add a function to the Vehicle prototype called start which returns the string "VROOM!"
Vehicle.prototype.start = function () {
    return "VROOM!"
}
// 3 - Add a function to the Vehicle prototype called toString which returns the string "The make, model, and year are" concatenated with the make, model and year property
Vehicle.prototype.toString = function () {
    return "The make, model, and year are " + this.make + " " + this.model + " " + this.year;
}
/* Examples 
    var vehicle = new Vehicle("Tractor", "John Deere", 1999)
    vehicle.toString() // 'The make, model, and year are Tractor John Deere 1999'
*/

// 4 - Create a constructor function for a Car. Each object created from the Car function should also have a make, model, and year and a property called numWheels which should be 4. The Car prototype should inherit all of the methods from the Vehicle prototype
function Car(make, model, year) {
    Vehicle.apply(this, arguments);
    this.numWheels = 4;
}
Car.prototype = Object.create(Vehicle.prototype)
Car.prototype.constructor = Car;

// 5 - Create a constructor function for a Motorcycle. Each object created from the Motorcycle function should also have a make, model, and year and a property called numWheels which should be 2. The Motorcycle prototype should inherit all of the methods from the Vehicle prototype
function Motorcycle(make, model, year) {
    Vehicle.apply(this, arguments);
    this.numWheels = 2;
}
Motorcycle.prototype = Object.create(Vehicle.prototype);
Motorcycle.prototype.constructor = Motorcycle;