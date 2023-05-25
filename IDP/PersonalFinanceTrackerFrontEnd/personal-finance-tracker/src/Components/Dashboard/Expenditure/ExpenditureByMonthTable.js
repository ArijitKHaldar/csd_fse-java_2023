import { useEffect, useState } from "react";
import axios from "axios";

function ExpenditureByMonthTable({ userId }) {
  const [expenditureData, setExpenditureData] = useState([]);
  const [month, setMonth] = useState(new Date().getMonth() + 1);
  const [categoryData, setCategoryData] = useState([]);

  useEffect(() => {
    axios
      .get(`/api/expenditure/v1/userid/${userId}/month/${month}`)
      .then((response) => {
        setExpenditureData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setExpenditureData([]);
        } else {
          console.log("Error fetching expenditure data:", error);
        }
      });

    axios
      .get(`/api/category/v1/view`)
      .then((response) => {
        setCategoryData(response.data);
      })
      .catch((error) => {
        if (error.response && error.response.status === 404) {
          setCategoryData([]);
        } else {
          console.log("Error fetching income data:", error);
        }
      });
  }, [month, userId]);

  const getCategoryTag = (categoryId) => {
    const category = categoryData.find(
      (category) => category.categoryId === categoryId
    );
    return category ? category.expenditureTag : "No Data";
  };

  const handleMonthChange = (e) => {
    setMonth(Number(e.target.value));
  };

  return (
    <div className="table-section">
      <div className="child-component expenditure-section">
        <h2>Expenditure</h2>
        <div className="month-dropdown">
          <label htmlFor="month">Select Month: </label>
          <select id="month" value={month} onChange={handleMonthChange}>
            <option value={1}>January</option>
            <option value={2}>February</option>
            <option value={3}>March</option>
            <option value={4}>April</option>
            <option value={5}>May</option>
            <option value={6}>June</option>
            <option value={7}>July</option>
            <option value={8}>August</option>
            <option value={9}>September</option>
            <option value={10}>October</option>
            <option value={11}>November</option>
            <option value={12}>December</option>
          </select>
        </div>
        <div className="expenditure-list">
          {expenditureData.length > 0 ? (
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Amount</th>
                  <th>Category</th>
                </tr>
              </thead>
              <tbody>
                {expenditureData.map((expenditure) => (
                  <tr key={expenditure.expenditureId}>
                    <td>{expenditure.expenditureId}</td>
                    <td>{expenditure.expenditureDate}</td>
                    <td>{expenditure.expenditureAmount}</td>
                    <td>{getCategoryTag(expenditure.categoryId)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <table>
              <thead>
                <tr>
                  <th>Id</th>
                  <th>Date</th>
                  <th>Amount</th>
                  <th>Category</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>No Data</td>
                  <td>No Data</td>
                  <td>No Data</td>
                  <td>No Data</td>
                </tr>
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
}
export default ExpenditureByMonthTable;
