import React, {Component} from 'react';
import './product.css';
import Add from "../../icons/add-button.png";

class Product extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="product">
                <img src={this.props.product.imageUrl} alt=""/>
                <p className="productName">{this.props.product.name}</p>
                <p className="price">{`单价:${this.props.product.price}元/${this.props.product.unit}`}</p>
                <img id="add_button" src={Add} alt="添加"/>
            </div>
        );
    }
}

export default Product;
