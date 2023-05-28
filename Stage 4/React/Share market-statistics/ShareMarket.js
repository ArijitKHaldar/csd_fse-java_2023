// Please do not make any changes in the given template
import React, { Component } from 'react';
import './Styles.css';


class ShareMarket extends Component {
  getCurrentDate() {
      const date = new Date();
      const day = date.getDate();
      const month = date.toLocaleDateString('en-GB', { month: 'numeric' });
      const year = date.getFullYear();
      const currentDate = `${day}-${month}-${year}`;
      
      return { currentDate };
  }
    // IMPLEMENT YOUR CODE HERE
    render(){
        const { currentDate } = this.getCurrentDate();
        return(
            <div>
                <h3>Share Market Statistics</h3>
                <h6>Commodity Report Dated:[{currentDate}]</h6>
                <HealthCareDB />
            </div>
            );
    }
  
}

class HealthCareDB extends Component {
  
  // IMPLEMENT YOUR CODE HERE
  render(){
        return(
            <div border="2">
                <table>
                    <tr>
                        <td>Commodity</td>
                        <td>Price</td>
                        <td>Change</td>
                        <td>Change %</td>
                    </tr>
                    <tr>
                        <td>Health Care</td>
                        <td>2500</td>
                        <td>100</td>
                        <td>2</td>
                    </tr>
                </table>
            </div>
            );
    }
  
}


export {HealthCareDB};
export default ShareMarket;

