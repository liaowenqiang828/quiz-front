import React, {Component} from "react";
import "./mall.css";
import Product from "./Product";
import Loading from "../loading/Loading";

class Mall extends Component {
    constructor(props) {
        super(props);
        this.state = {
            products: {},
            isLoading: false
        }
    }

    componentDidMount() {
        fetch("http://localhost:8080/products", {method: "GET"})
            .then(response => {
                return response.json();
            })
            .then(data => {
                console.log(data);
                this.setState({
                    products: data,
                    isLoading: true
                });
            })
            .catch(error => console.log(error))
    }

    render() {
        return (
            <div className="mall">
                {
                    !this.state.isLoading
                    ? <Loading/>
                    : this.state.products.map(product => {
                        return (<Product product={product} isLoading={this.state.isLoading} key={product.name}/>)
                        })
                }
            </div>
        )
    }


}
export default Mall;