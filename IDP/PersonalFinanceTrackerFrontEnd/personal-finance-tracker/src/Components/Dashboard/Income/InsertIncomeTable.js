import React, { useState } from "react";
import axios from "axios";
import "./InsertIncomeTable.css";

function InsertIncomeTable({ userId }) {
  const [incomeDate, setIncomeDate] = useState("");
  const [incomeAmount, setIncomeAmount] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const formattedDate = new Date(incomeDate).toISOString().substring(0, 10);
    const newIncome = {
      userId: userId,
      incomeDate: formattedDate,
      incomeAmount: incomeAmount,
    };
    axios
      .post("/api/income/v1", newIncome)
      .then((response) => {
        console.log("Income added:", response.data);
        setIncomeDate("");
        setIncomeAmount("");
        setSuccessMessage("Income added successfully.");
        setErrorMessage("");
      })
      .catch((error) => {
        console.log("Error adding income:", error);
        setSuccessMessage("");
        setErrorMessage("Error adding income. Please try again.");
      });
  };

  return (
    <div className="table-section">
      <div className="child-component income-section">
        <h2>Add Income</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="incomeDate">Income Date:</label>
            <input
              type="date"
              id="incomeDate"
              value={incomeDate}
              onChange={(e) => setIncomeDate(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="incomeAmount">Income Amount:</label>
            <input
              type="number"
              id="incomeAmount"
              placeholder="Enter amount in numeric format"
              value={incomeAmount}
              onChange={(e) => setIncomeAmount(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="button crud">
            Save
          </button>
        </form>
        {successMessage && <p className="success-message">{successMessage}</p>}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
      </div>
    </div>
  );
}

export default InsertIncomeTable;
