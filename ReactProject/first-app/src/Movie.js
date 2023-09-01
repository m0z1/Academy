import { Component } from "react";

class Movie extends Component {
    // eslint-disable-next-line no-useless-constructor
    constructor(props) {
        super(props)
        
    }
    render(){
        return(
            <div>
                <h1>무비 컴포넌트</h1>
                <div>{this.props.title}</div>
            </div>
        )
    }
}

export default Movie;