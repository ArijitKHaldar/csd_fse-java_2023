import React, { useState } from "react";
import { Button, Form, FormGroup, Input, Label, Progress } from "reactstrap";
import axios from "axios";

function FinancialGoal({ userId }) {
  const [date, setDate] = useState("");
  const [amount, setAmount] = useState("");
  const [percentageCompletion, setPercentageCompletion] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const currentDate = new Date();
    const selectedDate = new Date(date);

    if (selectedDate <= currentDate) {
      setError("Please input a future date");
      return;
    }

    try {
      const response = await axios.get(
        `/api/v1/goal/user/${userId}/date/${date}/amount/${amount}`
      );
      const completionPercentage = response.data;
      setPercentageCompletion(completionPercentage);
      setError(null);
    } catch (error) {
      setError("Data Inconsistencies Found. Cannot calculate");
      console.log("Error calculating savings completion:", error);
    }
  };

  return (
    <div className="table-section">
      <div className="goal child-component income-section">
        <h2>Financial Goal</h2>
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="date">Date:</Label>
            <Input
              type="date"
              id="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label for="amount">Amount:</Label>
            <Input
              type="number"
              id="amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
          </FormGroup>
          <Button type="submit" color="primary">
            Calculate
          </Button>
        </Form>
        {error ? (
          <div className="mt-4">
            <h4>{error}</h4>
          </div>
        ) : (
          percentageCompletion !== null && (
            <div className="mt-4">
              <h4>Savings Progress: {percentageCompletion}%</h4>
              <Progress value={percentageCompletion} />
            </div>
          )
        )}
      </div>
    </div>
  );
}

export default FinancialGoal;
