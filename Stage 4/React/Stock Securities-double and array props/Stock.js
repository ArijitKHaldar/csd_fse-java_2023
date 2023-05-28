import React, { Component } from 'react';
import './stock.css';
class Stock extends Component {
  
   //IMPLEMENT YOUR CODE HERE. "stockSymbol" , "marketPrice" and "options" are sent through one props data (i.e) as JSON
   render(){
       const { stockSymbol, marketPrice, options } = this.props.stocks;
       return (
      <div>
        <h1>Stock Securities</h1>
        <h3>Stock Symbol</h3>
        <p>{stockSymbol}</p>
        <h3>Market Price</h3>
        <p>{marketPrice}</p>
        <h3>Options</h3>
        {options.map((option, index) => (
          <p key={index}>{option}</p>
        ))}
      </div>
    );
   }
}


export default Stock;
