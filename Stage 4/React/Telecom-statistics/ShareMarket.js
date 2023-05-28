// Please do not make any changes in the given template

import React, { Component } from 'react';
import './Styles.css';


const ShareMarket=()=> {
  
    // IMPLEMENT YOUR CODE HERE
        const date = new Date();
        const day = date.getDate();
        const month = date.toLocaleDateString('en-GB', { month: 'numeric'});
        const year = date.getFullYear();
        const currentDate = `${day}-${month}-${year}`;
        
    return (
        <div>
            <h1>Share Market Statistics</h1>
            <h3>Commodity Report Dated:{currentDate}</h3>
            <Telecom />
        </div>
        );
    
  
}

class Telecom extends Component {
  
  // IMPLEMENT YOUR CODE HERE
  render() {
  const commodity = 'Telecom';
  const price = 3000;
  const change = 200;
  const percentage = 3;
      return (
          <div>
            <table border="2">
            <thead>
                <tr>
                    <th>Commodity</th>
                    <th>Price</th>
                    <th>Change</th>
                    <th>Change %</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>{commodity}</td>
                    <td>{price}</td>
                    <td>{change}</td>
                    <td>{percentage}</td>
                </tr>
            </tbody>
            </table>
          </div>
          );
  }

  
}

export {Telecom};
export default ShareMarket;