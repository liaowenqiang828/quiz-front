import React, { Component } from 'react';
import './product.css';
import { Button, message } from "antd";
import { PlusCircleOutlined } from "@ant-design/icons";
import Axios from 'axios';

class Product extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loading: false
        }
    }

    addProductToOrder = () => {
        this.setState({
            loading: true
        })
        const data = {
            name: this.props.product.name,
            price: this.props.product.price,
            unit: this.props.product.unit,
            imageUrl: this.props.product.imageUrl
        }

        Axios({
            url: "http://localhost:8080/product",
            method: "post",
            data: data
        }).then(response => {
            this.setState({
                loading: false
            })
            return response;
        }).catch(error => {
            console.log(error)
        })
    }

    render() {
        return (
            <div className="product">
                <div>
                    <img src={this.props.product.imageUrl} alt="" />
                    <p className="productName">{this.props.product.name}</p>
                    <p className="price">{`单价:${this.props.product.price}元/${this.props.product.unit}`}</p>
                </div>
                <button
                    disabled={this.state.loading}
                    onClick={this.addProductToOrder.bind(this)}
                />
            </div>
        );
    }
}

export default Product;
