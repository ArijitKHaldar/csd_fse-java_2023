// Please do not make any changes in the given code template
import React, { Component } from 'react';
import './styles.css';

//DO NOT CHANGE THE BELOW applicantsList INITIALIZATION
const applicantsList = [
	{
	   rollNo: 114,
	   name: 'Dean',
	   age: 22
	},
	{
	   rollNo: 325,
	   name: 'Carl Marx',
	   age: 25
	},
	{
	    rollNo: 120,
	    name: 'Bobby', 
	    age: 24
	},
	{
	    rollNo: 23,
	    name: 'Alice Dyana',
	    age: 22
	},
	{
	    rollNo: 35,
	    name: 'Ethan Donald',
	    age: 21
	},
  ];
 
class List extends React.Component {
	
	//IMPLEMENT YOUR CODE HERE. 
	constructor(props) {
    super(props);
    this.state = {
      sortedList: []
    };
  }

  componentDidMount = () => {
    const sortedList = [...applicantsList];
    sortedList.sort((a, b) => a.rollNo - b.rollNo);
    this.setState({ sortedList });
  };
	render(){
	    return( 
	        <div>
        <h1>ZION - The Training Academy</h1>
        <table>
          <thead>
            <tr>
              <th>Roll No</th>
              <th>Applicant Name</th>
              <th>Age</th>
            </tr>
          </thead>
          <tbody>
            {this.state.sortedList.map((applicant) => (
              <tr key={applicant.rollNo}>
                <td>{applicant.rollNo}</td>
                <td>{applicant.name}</td>
                <td>{applicant.age}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
	    );
	}
   
}

export default List;
