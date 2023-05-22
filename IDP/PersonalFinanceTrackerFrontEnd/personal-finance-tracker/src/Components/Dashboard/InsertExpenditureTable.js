import React, { useState, useEffect } from "react";
import axios from "axios";

function InsertExpenditureTable({ userId }) {
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

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!selectedCategoryId) {
      setErrorMessage("Please select a category.");
      return;
    }

    const formattedDate = new Date(expenditureDate)
      .toISOString()
      .substring(0, 10);
    const newExpenditure = {
      userId: userId,
      expenditureDate: formattedDate,
      expenditureAmount: expenditureAmount,
      categoryId: selectedCategoryId,
    };

    axios
      .post("/api/expenditure/v1", newExpenditure)
      .then((response) => {
        console.log("Expenditure added:", response.data);
        setExpenditureDate("");
        setExpenditureAmount("");
        setSelectedCategoryId("");
        setSuccessMessage("Expenditure added successfully.");
        setErrorMessage("");
      })
      .catch((error) => {
        console.log("Error adding expenditure:", error);
        setSuccessMessage("");
        setErrorMessage("Error adding expenditure. Please try again.");
      });
  };

  return (
    <div className="table-section">
      <div className="child-component expenditure-section">
        <h2>Add Expenditure</h2>
        <form onSubmit={handleSubmit}>
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
            Save
          </button>
        </form>
        {successMessage && <p className="success-message">{successMessage}</p>}
        {errorMessage && <p className="error-message">{errorMessage}</p>}
      </div>
    </div>
  );
}

export default InsertExpenditureTable;
