import React, {useEffect} from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import AppBar from '@mui/material/AppBar';
 import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import Avatar from '@mui/material/Avatar';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import axios from 'axios';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import FormLabel from '@mui/material/FormLabel';
import Paper from '@mui/material/Paper';
import Select from '@mui/material/Select';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import TextField from '@mui/material/TextField';
 import SearchIcon from '@mui/icons-material/Search';
import Accordion from '@mui/material/Accordion';
import AccordionDetails from '@mui/material/AccordionDetails';
import AccordionSummary from '@mui/material/AccordionSummary';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
/**
 * Phone numbers should be categorized
 * country, state (valid or not valid), country code and number.
 *
 */
const pages = ['All', 'Country', 'State', 'Code', 'Numbers'];

const columns = [
  { id: 'name', label: 'Name', minWidth: 170 },
  { id: 'phone', label: 'Phone', minWidth: 170 },

];
const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};


const BASEURL = 'http://localhost:8585/JumiaCustomer/';

 export default function CustomerPhones() {
    const [anchorElNav, setAnchorElNav] = React.useState(null);
    const [rows, setRowData] = React.useState([]);
    const [url, setUrl] = React.useState('listAll');
   const [page, setPage] = React.useState(0);
   const [totalElements, SetCount] = React.useState(10);
   const [rowsPerPage, setRowsPerPage] = React.useState(10);
   const [mounted, setMount] = React.useState(false);
  const [country, setCountry] = React.useState('');
  const [countries, setCountries] = React.useState([]);
  const [filter, setFilter] = React.useState('');
  const [category, setCategory] = React.useState('reset');
  const [expanded, setExpanded] = React.useState(false);

   useEffect(() => {
        (async function () {
            if (!mounted) {
              fetchData();
              fetchCountries();
              setMount(true);
            }
        })();
    }, [country,url,page, rowsPerPage]);


  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };


  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };


  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

   const fetchData = async () => {
     const fetchUrl =BASEURL+url+ '?page=' +page + '&size=' +rowsPerPage;
    await   axios.get(fetchUrl)
      .then(res => {
        const phoneData = res.data?.payload.content;
        SetCount(res.data?.payload?.totalElements)
        setRowData(phoneData);
      })
   }
   const fetchCountries = async () => {
     const fetchUrl =BASEURL+'listAllCountries';
    await   axios.get(fetchUrl)
      .then(res => {
        const data = res.data?.payload;
        setCountries(data);
       })
   }

   const handleChangePage = (event, newPage) => {
     console.log('handleChangePage=' + event.target.value);
     setPage(newPage);
          setMount(false)

  };

   const handleChangeRowsPerPage = (event) => {
     console.log('handleChangeRowsPerPage=' + event.target.value);
    setRowsPerPage(event.target.value);
     setPage(0);
     setMount(false);

  };
   const handleFilter = (event) => {
     console.log('handleFilter=' + event.target.value);
    const value = event.target.value;
     setFilter(value);
     setCategory('reset');
  };
   const handleSearch =async (event) => {
     console.log('country=' +JSON.stringify(country));

     setRowData([]);
     let filterurl;
     switch (filter) {
       case 'country':  filterurl ='filterByCountry?country='+country?.code;  break;
       case 'state':   filterurl ='filterByState?state=nosttate'; break;
        default:filterurl = url;
     }
          console.log('filterurl=' +filterurl);

     fetchUnpaginatedData(filterurl);

  };
   const handleCategorize =async (event) => {
     console.log('handleCategorize=' + event.target.value);
    //   'Country', 'State', 'Code', 'Numbers
     setFilter('');
     const selectedCategory = event.target.value;
     setCategory(selectedCategory);
     if (selectedCategory !== 'reset') {
       setRowData([]);
       const categoryurl = 'Categorize?category=' + selectedCategory;
       await fetchUnpaginatedData(categoryurl);
     } else {
setRowsPerPage(10);
       setPage(0);
        //  fetchData();
          setMount(false);
     }

   };

      const fetchUnpaginatedData = async (url) => {
     const fetchUrl =BASEURL+url;
    await   axios.get(fetchUrl)
      .then(res => {
        const data = res.data?.payload;
        setRowData(data);
        const count =data.length;
        SetCount(count)
         setRowsPerPage(parseInt(count));
     setPage(0);
       })
   }

   const handleSelectChange = (event) => {
     console.log('handleSelectChange=' +JSON.stringify( event.target.value));
    const {
      target: { value },
    } = event;
    setCountry(value );
  };

  return (
    <React.Fragment>
      <CssBaseline />
      <Container maxWidth="lg">
        <div sx={{ bgcolor: '#cfe8fc' }} >
           <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mr: 2, display: { xs: 'none', md: 'flex' } }}
          >
              <Avatar
        alt="Jumia App"
        src="/jumia.jpg"
        sx={{ width: 56, height: 56 }}
      />
          </Typography>

          <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'left',
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: 'block', md: 'none' },
              }}
            >
              {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <Typography textAlign="center">{page}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}
          >
                <Avatar
        alt="Jumia App"
        src="/jumia.jpg"
        sx={{ width: 56, height: 56 }}
      />
          </Typography>


        </Toolbar>
      </Container>
          </AppBar>
          {/* <PhoneTable/> */}
          <div style={{ height: 600, width: '100%' }}>
            <div>
              <FormControl>
                {/* ['All', 'Country', 'State', 'Code', 'Numbers']; */}

      <FormLabel id="demo-row-radio-buttons-group-label">Categorize by:</FormLabel>
      <RadioGroup
                  row
                  aria-labelledby="demo-row-radio-buttons-group-label"
                  name="row-radio-buttons-group"
                  value={category}
                  onChange={handleCategorize}
      >
        <FormControlLabel value="country" control={<Radio />} label="Country" />
        <FormControlLabel value="state" control={<Radio />} label="State" />
        <FormControlLabel value="code" control={<Radio />} label="Code" />
        <FormControlLabel value="number"  control={<Radio />} label="Numbers" />
        <FormControlLabel value="reset"  control={<Radio />} label="Reset" />
      </RadioGroup>
    </FormControl>
            </div>
  <Paper elevation={3} >

            <div>

              <FormControl>


      <FormLabel id="demo-row-radio-buttons-group-label">Filter by:</FormLabel>
      <RadioGroup
        row
        aria-labelledby="demo-row-radio-buttons-group-label"
                  name="row-radio-buttons-group"
                    onChange={handleFilter}
                    value={filter}
      >
        <FormControlLabel value="country" control={<Radio />} label="Country" />
        <FormControlLabel value="state" control={<Radio />} label="State" />
      </RadioGroup>
    </FormControl>

            </div>


            <div>
    {( filter==='country')&&
  <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel id="demo-multiple-name-label">Select Country</InputLabel>
        <Select
          labelId="demo-multiple-name-label"
          id="demo-multiple-name"
          value={country}
          onChange={handleSelectChange}
          input={<OutlinedInput label="Name" />}
          MenuProps={MenuProps}
        >
          {countries.map((cnt) => (
            <MenuItem
              key={cnt?.code}
              value={cnt}
            >
              {cnt?.name}
            </MenuItem>
          ))}
        </Select>
                </FormControl>
        }
                {(filter == 'state') && <TextField id="outlined-basic" label="enter state" variant="outlined" />}
                <Button variant="contained" color="primary"
                  onClick={handleSearch}
                >Search
  <SearchIcon />
</Button>

            </div>
            </Paper>
            { (category==='reset')&&
              <div>
    <TableContainer sx={{ maxHeight: 600 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows
               .map((row) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1} key={row.code}>
                    {columns.map((column) => {
                      const value = row[column.id];
                      return (
                        <TableCell key={column.id} align={column.align}>
                          {column.format && typeof value === 'number'
                            ? column.format(value)
                            : value}
                        </TableCell>
                      );
                    })}
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 50]}
        component="div"
        count={totalElements}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
              </div>
            }
            {(category !== 'reset') &&
              <div>
                {rows
                .map((row) => {
                  const rowinfo = row?.customers;
                    return (
      <Accordion expanded={expanded === row?.categoryName} onChange={handleChange(row?.categoryName)}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1bh-content"
          id="panel1bh-header"
        >
          <Typography sx={{ width: '33%', flexShrink: 0 }}>
           {row?.categoryName}
          </Typography>
          <Typography sx={{ color: 'text.secondary' }}>  {row?.categoryName}</Typography>
        </AccordionSummary>
        <AccordionDetails>
                          {
                      <TableContainer sx={{ maxHeight: 400 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rowinfo
               .map((cus) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1} key={cus.code}>
                    {columns.map((column) => {
                      const value = cus[column.id];
                      return (
                        <TableCell key={column.id} align={column.align}>
                          {column.format && typeof value === 'number'
                            ? column.format(value)
                            : value}
                        </TableCell>
                      );
                    })}
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
          }
        </AccordionDetails>
      </Accordion>
     );
              })}
           </div>
            }
          </div>


        </div>
      </Container>
    </React.Fragment>
  );
}
