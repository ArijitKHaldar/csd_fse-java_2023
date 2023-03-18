"use strict";
class Employee 
{
//fill code here
    constructor(name, designation, year_of_experience)
    {
        this.name = name;
        this.designation = designation;
        this.year_of_experience = year_of_experience;
    }
}

function createEmployee(name, designation, year_of_experience)
{
//fill code here
    return new Employee(name, designation, year_of_experience);
}

function validateObject(employee)
{
//fill code here
    if(employee instanceof Employee)
        return true;
    else
        return false;
}
function displayEmployee(name, designation, year_of_experience)
{
    const emp = createEmployee(name, designation, year_of_experience);
    if(validateObject(emp))
    {
        var serviceYears = new Date().getFullYear() - emp.year_of_experience;
        
        var result = emp.name+" is serving the position of "+emp.designation+" since "+serviceYears;
        return result;
    }
}
console.log(displayEmployee("Jerold","Manager",18));