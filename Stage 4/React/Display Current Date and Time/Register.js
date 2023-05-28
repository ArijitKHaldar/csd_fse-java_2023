// Please do not make any changes in the given template
import React, { Component } from 'react';
import './Styles.css';


class Register extends Component {
  
    // IMPLEMENT YOUR CODE HERE
    render() {
        return (
        <div>
            <h1>Stock Securities</h1>
            <p>Welcome to one of the leading online platforms for stock trading in the country</p>
            <TimeComp />
        </div>
        );
    }

}

class TimeComp extends Component {
  
  // IMPLEMENT YOUR CODE HERE
  getCurrentDateTime() {
      const date = new Date();
      const day = date.getDate();
      const month = date.toLocaleDateString('en-GB', { month: 'numeric' });
      const year = date.getFullYear();
      const currentDate = `${day}-${month}-${year}`;
      const options = { hour12: false, hour: '2-digit', minute: '2-digit', second: '2-digit' };
      const currentTime = new Date().toLocaleTimeString('en-US', options);
      return { currentDate, currentTime };
  }
  render() {
      const { currentDate, currentTime } = this.getCurrentDateTime();
        return (
            <div>
                <p>You have successfully registered with us on {currentDate} at {currentTime}</p>
            </div>
        );
  }

}

export {TimeComp};
export default Register;