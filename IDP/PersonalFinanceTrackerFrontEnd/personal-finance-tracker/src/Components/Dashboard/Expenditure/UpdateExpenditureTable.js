import React, { useState, useEffect } from "react";
import axios from "axios";

function UpdateExpenditureTable({ userId }) {
  const [expenditureId, setExpenditureId] = useState("");
  const [expenditureDate, setExpenditureDate] = useState("");
  const [expenditureAmount, setExpenditureAmount] = useState("");
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState("");
  const [newCategoryName, setNewCategoryName] = useState("");
  const [showNewCategoryInput, setShowNewCategoryInput] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    axios
      .get("/api/category/v1/view")
      .then((response) => {
        setCategoryOptions(response.data);
      })
      .catch((error) => {
        console.log("Error fetching categories:", error);
        setCategoryOptions([]);
      });
  }, []);

  const handleCategoryChange = (e) => {
    const selectedCategory = categoryOptions.find(
      (category) => category.categoryId === e.target.value
    );
    setSelectedCategoryId(e.target.value);
    setShowNewCategoryInput(e.target.value === "new" && !selectedCategory);
  };

  const handleNewCategoryNameChange = (e) => {
    setNewCategoryName(e.target.value);
  };

  const handleNewCategoryConfirm = () => {
    if (newCategoryName.trim() === "") {
      setErrorMessage("Category name cannot be empty.");
      return;
    }

    axios
      .post("/api/category/v1", { categoryName: newCategoryName.toUpperCase() })
      .then((response) => {
        const { categoryId } = response.data;
        setSelectedCategoryId(categoryId);
        setShowNewCategoryInput(false);
        setNewCategoryName("");
        setSuccessMessage("New category added successfully.");
        setErrorMessage("");
      })
      .catch((error) => {
        console.log("Error adding new category:", error);
        setSuccessMessage("");
        setErrorMessage("Error adding new category. Please try again.");
      });
  };

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
          setSelectedCategoryId(expenditure.categoryId);
          setShowNewCategoryInput(false);
          setSuccessMessage("");
          setErrorMessage("");
        } else {
          setExpenditureDate("");
          setExpenditureAmount("");
          setSelectedCategoryId("");
          setShowNewCategoryInput(false);
          setSuccessMessage("");
          setErrorMessage(
            "Expenditure not found. Please enter a valid expenditure ID."
          );
        }
      })
      .catch((error) => {
        console.log("Error fetching expenditure:", error);
        setSuccessMessage("");
        setErrorMessage("Error fetching expenditure. Please try again.");
      });
  };

  const handleUpdate = (e) => {
    e.preventDefault();

    if (!selectedCategoryId) {
      setErrorMessage("Please select a category.");
      return;
    }

    const updatedExpenditure = {
      expenditureDate,
      expenditureAmount,
      categoryId: selectedCategoryId,
    };

    axios
      .put(
        `/api/expenditure/v1/update?expenditureId=${expenditureId}`,
        updatedExpenditure
      )
      .then((response) => {
        console.log("Expenditure updated:", response.data);
        setExpenditureId("");
        setExpenditureDate("");
        setExpenditureAmount("");
        setSelectedCategoryId("");
        setShowNewCategoryInput(false);
        setSuccessMessage("Expenditure updated successfully.");
        setErrorMessage("");
      })
      .catch((error) => {
        console.log("Error updating expenditure:", error);
        setSuccessMessage("");
        setErrorMessage("Error updating expenditure. Please try again.");
      });
  };

  const showExpenditureFields = expenditureDate && expenditureAmount;

  return (
    <div className="table-section">
      <div className="child-component expenditure-section">
        <h2>Update Expenditure</h2>
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
          <form onSubmit={handleUpdate}>
            <div className="form-group">
              <label htmlFor="expenditureDate">Expenditure Date:</label>
              <input
                type="date"
                id="expenditureDate"
                value={expenditureDate}
                onChange={(e) => setExpenditureDate(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="expenditureAmount">Expenditure Amount:</label>
              <input
                type="number"
                id="expenditureAmount"
                placeholder="Enter amount in numeric format"
                value={expenditureAmount}
                onChange={(e) => setExpenditureAmount(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="category">Category:</label>
              <select
                id="category"
                value={selectedCategoryId}
                onChange={handleCategoryChange}
                required
              >
                <option value="">Select a category</option>
                {categoryOptions.map((category) => (
                  <option key={category.categoryId} value={category.categoryId}>
                    {category.expenditureTag}
                  </option>
                ))}
                <option value="new">Add New Category</option>
              </select>
            </div>
            {showNewCategoryInput && (
              <div className="form-group">
                <label htmlFor="newCategory">New Category Name:</label>
                <input
                  type="text"
                  id="newCategory"
                  placeholder="Enter new category name"
                  value={newCategoryName}
                  onChange={handleNewCategoryNameChange}
                  required
                />
                <button
                  type="button"
                  className="button crud confirm-button"
                  onClick={handleNewCategoryConfirm}
                >
                  Confirm
                </button>
              </div>
            )}
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

export default UpdateExpenditureTable;
