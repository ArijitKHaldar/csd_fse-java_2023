import React, { Component } from 'react';
import './stock.css';
class Stock extends Component {
  
  //IMPLEMENT YOUR CODE HERE. "stockName" and "stockShares" is sent through one props data (i.e) as JSON
	render(){
  const { stockName, stockShares } = this.props.dashValues;
	    return (
      <div>
        <h1>Stock Securities</h1>
        <h2>Stock Name: {stockName}</h2>
        <h2>Stock Shares: {stockShares}</h2>
      </div>
    );
	}
}
  



export default Stock;
