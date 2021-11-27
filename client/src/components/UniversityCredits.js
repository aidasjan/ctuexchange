import React from "react";
import { useState } from "react/cjs/react.development";
import { Container, Table } from "reactstrap";
import { ButtonModal } from "./ButtonModal";

export default function UniversityCredits() {
  const [universityCredits, setUniversityCredits] = useState(null);

  const handleClick = async () => {
    fetch("/api/universities/credits").then((response) => response.json()).then(
      (result) => { 
        setUniversityCredits(result);
      }
    );
  };

  return (
    <ButtonModal text="University Credits" className="btn btn-success">
      <Container className="text-center">
        <button onClick={handleClick} className="btn btn-primary mt-4 mb-4">
          Get University Credits
        </button>
        {universityCredits ? (
          <Table>
            <thead>
              <th>Name</th>
              <th>Total Student Credits</th>
            </thead>
            <tbody>
              {universityCredits.map((university) => (
                <tr>
                  <td>{university.name}</td>
                  <td>{university.credits}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : null}
      </Container>
    </ButtonModal>
  );
}
