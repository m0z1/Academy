

const MyDevice = ({mydata}) => {
return(

        
       mydata.myDeviceData.map((device, i) =>{
        return(
            <div key={i}>
                키 : {i} <br/>
                이름 : {device.name}<br/>
                ram : {device.ram}<br/>
                touch : {device.touch}<br/>
                face: {device.face}<br/><br/><br/>
    

            </div>
        )
       
    })
      
        
)

}
export default MyDevice;