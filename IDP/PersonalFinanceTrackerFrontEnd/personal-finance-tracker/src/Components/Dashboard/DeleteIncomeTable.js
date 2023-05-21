import React, { useState } from "react";
import axios from "axios";

function DeleteIncomeTable({ userId }) {
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
          setIncomeDate("");
          setIncomeAmount("");
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

  const handleDelete = () => {
    axios
      .delete(`/api/income/v1/delete/id/${incomeId}`)
      .then((response) => {
        console.log("Income deleted:", response.data);
        setSuccessMessage("Income deleted successfully.");
        setErrorMessage("");
        setIncomeId("");
        setIncomeDate("");
        setIncomeAmount("");
      })
      .catch((error) => {
        console.log("Error deleting income:", error);
        setSuccessMessage("");
        setErrorMessage("Error deleting income. Please try again.");
      });
  };

  const showIncomeFields = incomeDate && incomeAmount;

  return (
    <div className="table-section">
      <div className="child-component income-section">
        <h2>Delete Income</h2>
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
          <>
            <div className="form-group">
              <label htmlFor="incomeDate">Income Date:</label>
              <input type="text" id="incomeDate" value={incomeDate} readOnly />
            </div>
            <div className="form-group">
              <label htmlFor="incomeAmount">Income Amount:</label>
              <input
                type="text"
                id="incomeAmount"
                value={incomeAmount}
                readOnly
              />
            </div>
            <button
              type="button"
              className="button crud"
              onClick={handleDelete}
            >
              Delete
            </button>
          </>
        )}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        {successMessage && <p className="success-message">{successMessage}</p>}
      </div>
    </div>
  );
}

export default DeleteIncomeTable;
