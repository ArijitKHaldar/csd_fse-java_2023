import React, { useState } from "react";
import axios from "axios";

function UpdateIncomeTable({ userId }) {
  const [incomeId, setIncomeId] = useState("");
  const [incomeDate, setIncomeDate] = useState("");
  const [incomeAmount, setIncomeAmount] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleGetDetails = () => {
    axios
      .get(`/api/income/v1/userid/${userId}`)
      .then((response) => {
        const income = response.data.find(
          (income) => income.incomeId === Number(incomeId)
        );
        if (income) {
          setIncomeDate(income.incomeDate);
          setIncomeAmount(income.incomeAmount);
          setSuccessMessage("");
          setErrorMessage("");
        } else {
          setSuccessMessage("");
          setErrorMessage("Income not found. Please enter a valid income ID.");
        }
      })
      .catch((error) => {
        console.log("Error fetching income:", error);
        setSuccessMessage("");
        setErrorMessage("Error fetching income. Please try again.");
      });
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    const updatedIncome = {
      incomeDate,
      incomeAmount,
    };
    axios
      .put(`/api/income/v1/update?incomeId=${incomeId}`, updatedIncome)
      .then((response) => {
        console.log("Income updated:", response.data);
        setSuccessMessage("Income updated successfully.");
        setErrorMessage("");
      })
      .catch((error) => {
        console.log("Error updating income:", error);
        setSuccessMessage("");
        setErrorMessage("Error updating income. Please try again.");
      });
  };

  const showIncomeFields = incomeDate && incomeAmount;

  return (
    <div className="table-section">
      <div className="child-component income-section">
        <h2>Update Income</h2>
        <div className="get-details-section">
          <div className="form-group">
            <label htmlFor="incomeId">Income ID:</label>
            <input
              type="number"
              id="incomeId"
              value={incomeId}
              onChange={(e) => setIncomeId(e.target.value)}
              required
            />
          </div>
          <button
            type="button"
            className="button crud"
            onClick={handleGetDetails}
          >
            Get Details
          </button>
        </div>
        {showIncomeFields && (
          <form onSubmit={handleUpdate}>
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
              Update
            </button>
          </form>
        )}
        {successMessage && <p className="success-message">{successMessage}</p>}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
      </div>
    </div>
  );
}

export default UpdateIncomeTable;
