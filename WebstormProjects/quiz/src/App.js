import React from 'react';
import { BrowserRouter, NavLink, Route} from "react-router-dom";
import './App.css';
import HomeIcon from "./icons/home.png";
import Cart from "./icons/cart.png";
import Add from "./icons/add.png";
import Mall from "./components/mall/Mall";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
          <header className="App-header">
              <ul>
                  <li>
                      <NavLink exact to="/" activeClassName="active">
                          {/*eslint-disable-next-line*/}
                          <img src={HomeIcon} alt=""/> 商城
                      </NavLink>
                  </li>
                  <li>
                      <NavLink exact to="/order" activeClassName="active">
                          {/*eslint-disable-next-line*/}
                          <img src={Cart} alt=""/> 订单
                      </NavLink>
                  </li>
                  <li>
                      <NavLink exact to="/add" activeClassName="active">
                          {/*eslint-disable-next-line*/}
                          <img src={Add} alt=""/> 添加商品
                      </NavLink>
                  </li>
              </ul>
          </header>
            <Route exact path="/" component={Mall}></Route>
        </BrowserRouter>
    </div>
  );
}

export default App;
