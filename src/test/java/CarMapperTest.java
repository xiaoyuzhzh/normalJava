import org.iceberg.mapstruct.Car;
import org.iceberg.mapstruct.CarDto;
import org.iceberg.mapstruct.CarMapper;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by xiaoyuzhzh on 11/8/2020.
 */
public class CarMapperTest {

    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car( "Morris", 5, "SEDAN" );


        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        Assert.assertNotNull( carDto );
//        assertThat( carDto.getMake() ).isEqualTo( "Morris" );
//        assertThat( carDto.getSeatCount() ).isEqualTo( 5 );
//        assertThat( carDto.getType() ).isEqualTo( "SEDAN" );
    }
}
