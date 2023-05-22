import React, { useState } from "react";
import axios from "axios";

function DeleteExpenditureTable({ userId }) {
  const [expenditureId, setExpenditureId] = useState("");
  const [expenditureDate, setExpenditureDate] = useState("");
  const [expenditureAmount, setExpenditureAmount] = useState("");
  const [categoryData, setCategoryData] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleGetDetails = () => {
    axios
      .get(`/api/expenditure/v1/userid/${userId}`)
      .then((response) => {
        const expenditure = response.data.find(
          (expenditure) => expenditure.expenditureId === Number(expenditureId)
        );
        if (expenditure) {
          setExpenditureDate(expenditure.expenditureDate);
          setExpenditureAmount(expenditure.expenditureAmount);
          setSuccessMessage("");
          setErrorMessage("");

          axios
            .get(`/api/category/v1/view`)
            .then((categoryResponse) => {
              const category = categoryResponse.data.find(
                (category) => category.categoryId === expenditure.categoryId
              );
              if (category) {
                setCategoryData(category.expenditureTag);
                setSuccessMessage("");
                setErrorMessage("");
              } else {
                setCategoryData("");
                setSuccessMessage("");
                setErrorMessage("Category not found.");
              }
            })
            .catch((error) => {
              console.log("Error fetching category:", error);
              setSuccessMessage("");
              setErrorMessage("Error fetching category. Please try again.");
            });
        } else {
          setExpenditureDate("");
          setExpenditureAmount("");
          setCategoryData("");
          setSuccessMessage("");
          setErrorMessage(
            "Expenditure not found. Please enter a valid expenditure ID."
          );
        }
      })
      .catch((error) => {
        console.log("Error fetching expenditure:", error);
        setExpenditureDate("");
        setExpenditureAmount("");
        setCategoryData("");
        setSuccessMessage("");
        setErrorMessage("Error fetching expenditure. Please try again.");
      });
  };

  const handleDelete = () => {
    axios
      .delete(`/api/expenditure/v1/delete/id/${expenditureId}`)
      .then((response) => {
        console.log("Expenditure deleted:", response.data);
        setSuccessMessage("Expenditure deleted successfully.");
        setErrorMessage("");
        setExpenditureId("");
        setExpenditureDate("");
        setExpenditureAmount("");
        setCategoryData("");
      })
      .catch((error) => {
        console.log("Error deleting expenditure:", error);
        setSuccessMessage("");
        setErrorMessage("Error deleting expenditure. Please try again.");
      });
  };

  const showExpenditureFields = expenditureDate && expenditureAmount;

  return (
    <div className="table-section">
      <div className="child-component expenditure-section">
        <h2>Delete Expenditure</h2>
        <div className="get-details-section">
          <div className="form-group">
            <label htmlFor="expenditureId">Expenditure ID:</label>
            <input
              type="number"
              id="expenditureId"
              value={expenditureId}
              onChange={(e) => setExpenditureId(e.target.value)}
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
        {showExpenditureFields && (
          <>
            <div className="form-group">
              <label htmlFor="expenditureDate">Expenditure Date:</label>
              <input
                type="text"
                id="expenditureDate"
                value={expenditureDate}
                readOnly
              />
            </div>
            <div className="form-group">
              <label htmlFor="expenditureAmount">Expenditure Amount:</label>
              <input
                type="text"
                id="expenditureAmount"
                value={expenditureAmount}
                readOnly
              />
            </div>
            <div className="form-group">
              <label htmlFor="category">Category</label>
              <input type="text" id="category" value={categoryData} readOnly />
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
export default DeleteExpenditureTable;
