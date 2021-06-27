import React from 'react';
import PropTypes from 'prop-types';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import { Button } from '@material-ui/core';
import axios from 'axios';

const AdminTableRow = (props) => {
  const { row, labelId } = props;

  const handleDelete = async () => {
    const result = await axios.delete(`http://localhost:8000/api/plants/${row.id}`);

    if (result.status === 202) window.location.reload();
  };

  const handleEdit = async () => {

  };

  return (
    <TableRow
      hover
      role="checkbox"
      tabIndex={-1}
      key={row.id}
    >
      <TableCell component="th" id={labelId} scope="row">
        {row.id}
      </TableCell>
      <TableCell>{row.commonName}</TableCell>
      <TableCell>{row.scientificName}</TableCell>
      <TableCell>{row.price}</TableCell>
      <TableCell>
        <Button color="primary" onClick={handleEdit}>Edit</Button>
      </TableCell>
      <TableCell>
        <Button color="secondary" onClick={handleDelete}>Delete</Button>
      </TableCell>
    </TableRow>
  );
};

AdminTableRow.propTypes = {
  // eslint-disable-next-line react/forbid-prop-types
  row: PropTypes.object.isRequired,
  labelId: PropTypes.string.isRequired,
};

export default AdminTableRow;
