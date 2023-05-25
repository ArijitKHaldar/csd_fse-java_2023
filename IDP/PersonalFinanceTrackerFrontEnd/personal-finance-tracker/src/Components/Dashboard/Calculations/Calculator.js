import React, { useState } from "react";
import "./Calculator.css";

function Calculator() {
  const [display, setDisplay] = useState("");

  const handleClick = (value) => {
    setDisplay((prevDisplay) => prevDisplay + value);
  };

  const handleClear = () => {
    setDisplay("");
  };

  const handleCalculate = () => {
    let result;
    try {
      const calculate = new Function(`return ${display}`);
      result = calculate();
      setDisplay(result.toString());
    } catch (error) {
      console.log("Calculation error:", error);
      setDisplay("Error");
    }
  };

  return (
    <div id="calculator-container">
      <div className="calculator">
        <div className="display">{display}</div>
        <div className="buttons">
          <button onClick={() => handleClick("7")}>7</button>
          <button onClick={() => handleClick("8")}>8</button>
          <button onClick={() => handleClick("9")}>9</button>
          <button onClick={() => handleClick("+")}>+</button>
          <button onClick={() => handleClick("4")}>4</button>
          <button onClick={() => handleClick("5")}>5</button>
          <button onClick={() => handleClick("6")}>6</button>
          <button onClick={() => handleClick("-")}>-</button>
          <button onClick={() => handleClick("1")}>1</button>
          <button onClick={() => handleClick("2")}>2</button>
          <button onClick={() => handleClick("3")}>3</button>
          <button onClick={() => handleClick("*")}>*</button>
          <button onClick={() => handleClick("0")}>0</button>
          <button onClick={() => handleClick(".")}>.</button>
          <button onClick={() => handleClick("/")}>/</button>
          <button onClick={() => handleClick("%")}>mod</button>
          <button className="clear" onClick={handleClear}>
            Clear
          </button>
          <button className="calculate" onClick={handleCalculate}>
            Calculate
          </button>
        </div>
      </div>
    </div>
  );
}

export default Calculator;
