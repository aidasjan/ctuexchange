import React from "react";
import { Container } from "reactstrap";
import Crud from "../components/Crud";
import UniversityCredits from "../components/UniversityCredits";

export default function Universities(props) {
  return (
    <Container>
      <Crud
        url="/api/universities"
        tableColumns={["Id", "Name", "City"]}
        getTableRowItems={(record) => [record.id, record.name, record.city]}
        formInputs={[
          { key: "name", label: "Name" },
          { key: "city", label: "City" },
        ]}
      />
      <UniversityCredits />
    </Container>
  );
}
