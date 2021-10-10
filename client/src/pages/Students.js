import React, { useEffect, useState } from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";

export default function Students(props) {
  const [universities, setUniversities] = useState([]);

  useEffect(() => {
    fetch("/api/universities").then((response) =>
      response.json().then((results) => setUniversities(results))
    );
  }, []);

  return (
    <Container>
      <Crud
        url="/api/students"
        tableColumns={["Id", "Name", "Surname"]}
        getTableRowItems={(record) => [record.id, record.name, record.surname]}
        formInputs={[
          { key: "name", label: "Name" },
          { key: "surname", label: "Surname" },
          {
            key: "universityId",
            label: "University",
            options: universities.map((item) => ({
              value: item.id,
              label: item.name,
            })),
            findId: (record) => record.university.id,
          },
        ]}
      />
    </Container>
  );
}
