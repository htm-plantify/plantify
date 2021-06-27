import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableContainer from '@material-ui/core/TableContainer';
import AdminTableToolbar from './AdminTableToolbar';
import AdminTableHead from './AdminTableHead';
import AdminTableRow from './AdminTableRow';

const useStyles = makeStyles((theme) => ({
  root: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(2),
  },
  table: {
    minWidth: 750,
  },
}));

const AdminTable = (props) => {
  const classes = useStyles();

  const { rows, headCells } = props;

  return (
    <div className={classes.root}>
      <AdminTableToolbar />
      <TableContainer>
        <Table
          className={classes.table}
          aria-labelledby="tableTitle"
          size="small"
          aria-label="Admin table"
          stickyHeader
        >
          <AdminTableHead
            classes={classes}
            headCells={headCells}
          />
          <TableBody>
            {rows.map((row, index) => {
              const labelId = `enhanced-table-checkbox-${index}`;

              return (
                <AdminTableRow row={row} labelId={labelId} />
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

AdminTable.propTypes = {
  rows: PropTypes.arrayOf(PropTypes.object).isRequired,
  headCells: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default AdminTable;
