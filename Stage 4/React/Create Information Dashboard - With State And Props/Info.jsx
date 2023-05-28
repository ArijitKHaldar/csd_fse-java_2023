import React, { Component } from 'react';
import './style.css';



class Info extends Component {
	//IMPLEMENT YOUR CODE HERE. 
    render()
    {
    return(
        <div>
        <h1>Share Market Investments</h1>
        <h2>Hello Client!</h2>
        <HealthCare client={this.props.client} />
        <TeleCom client={this.props.client} />
      </div>
        );
    }   
}
class HealthCare extends React.Component{
	//IMPLEMENT YOUR CODE HERE. Declare state variable here
    render(){
        const { hc_stockNotation, hc_quantity, hc_stockValue } = this.props.client;

    const hc_totalValue = hc_quantity * hc_stockValue;
    return (
      <div>
        <h2>Client's Investment in HealthCare</h2>
        <table border="1">
          <tr>
            <th>Stock Notation</th>
            <th>Quantity</th>
            <th>Stock Value</th>
            <th>Total Value</th>
          </tr>
          <tr>
            <td>{hc_stockNotation}</td>
            <td>{hc_quantity}</td>
            <td>{hc_stockValue}</td>
            <td>{hc_totalValue}</td>
          </tr>
        </table>
      </div>
    );
    }

}  


class TeleCom extends React.Component{
	//IMPLEMENT YOUR CODE HERE. Declare state variable here

render(){
    const { tc_stockNotation, tc_quantity, tc_stockValue } = this.props.client;

    const tc_totalValue = tc_quantity * tc_stockValue;
return (
      <div>
        <h2>Client's Investment in TeleCom</h2>
        <table border="1">
          <tr>
            <th>Stock Notation</th>
            <th>Quantity</th>
            <th>Stock Value</th>
            <th>Total Value</th>
          </tr>
          <tr>
            <td>{tc_stockNotation}</td>
            <td>{tc_quantity}</td>
            <td>{tc_stockValue}</td>
            <td>{tc_totalValue}</td>
          </tr>
        </table>
      </div>
    );
}

}  
 
export {TeleCom}
export {HealthCare}
export default Info;
