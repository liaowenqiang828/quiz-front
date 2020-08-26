import React from 'react';
import { BrowserRouter, NavLink, Route} from "react-router-dom";
import './App.css';
import { HomeOutlined, PlusOutlined,  ShoppingCartOutlined} from "@ant-design/icons";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
          <header className="App-header">
              <ul>
                  <li>
                      <NavLink exact to="/" activeClassName="active">
                          <HomeOutlined/> 商城
                      </NavLink>
                  </li>
                  <li>
                      <NavLink exact to="/order" activeClassName="active">
                          <ShoppingCartOutlined/> 订单
                      </NavLink>
                  </li>
                  <li>
                      <NavLink exact to="/add" activeClassName="active">
                          <PlusOutlined/> 添加商品
                      </NavLink>
                  </li>
              </ul>
          </header>
        </BrowserRouter>
    </div>
  );
}

export default App;
