import { Component } from "react";

class Mydevice1 extends Component{

    render() {
        const {mydata} = this.props;
        return(
            <div>
                {
                mydata.map((device, index) => {
                    return(
                        <div>
                            키 : {index}<br/>
                               이름 : {device.name}<br/>
                ram : {device.ram}<br/>
                touch : {device.touch}<br/>
                face: {device.face}<br/><br/><br/>
                        </div>
                    )

                })
    }
            </div>
        )
    }
}

export default Mydevice1;