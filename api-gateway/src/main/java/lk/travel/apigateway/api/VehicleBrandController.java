package lk.travel.apigateway.api;

import lk.travel.apigateway.constant.SecurityConstant;
import lk.travel.apigateway.dto.VehicleBrandDTO;
import lk.travel.apigateway.dto.VehicleCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/gateway/vehicle/brand")
@RequiredArgsConstructor
public class VehicleBrandController {
    private final String URL = SecurityConstant.URL+"8085/api/v1/vehicle/brand";
    @PostMapping
    public Mono<VehicleBrandDTO> saveVehicleBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers, @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        return WebClient.create(URL).post().body(Mono.just(vehicleBrandDTO), VehicleBrandDTO.class).headers(h -> h.set(HttpHeaders.AUTHORIZATION,headers)).retrieve().bodyToMono(VehicleBrandDTO.class);
    }

    @PutMapping
    public ResponseEntity<VehicleBrandDTO> updateVehicleBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers, @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        return WebClient.create(URL).put().body(Mono.just(vehicleBrandDTO), VehicleBrandDTO.class).headers(h -> h.set(HttpHeaders.AUTHORIZATION,headers)).retrieve().toEntity(VehicleBrandDTO.class).block();
    }


    @GetMapping(path = "search/{vehicleID}")
    public ResponseEntity searchVehicleBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers, @PathVariable("vehicleID") int vehicleID) {
        try {
            return WebClient.create(URL + "/search/" + vehicleID).get().headers(h -> h.set(HttpHeaders.AUTHORIZATION,headers)).retrieve().toEntity(VehicleBrandDTO.class).block();

        } catch (Exception e) {
            throw new RuntimeException("VehicleBrand Not Exists..!!");
        }
    }



    @DeleteMapping(path = "{vehicleID}")
    public ResponseEntity<Void> deleteVehicleBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers, @PathVariable int vehicleID) {
        return WebClient.create(URL + "/" + vehicleID).delete().headers(h -> h.set(HttpHeaders.AUTHORIZATION,headers)).retrieve().toEntity(Void.class).block();

    }
    @GetMapping
    public ResponseEntity getAllVehicleBrand(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers) {
        VehicleBrandDTO[] body = WebClient.create(URL).get().headers(h -> h.set(HttpHeaders.AUTHORIZATION, headers)).retrieve().toEntity(VehicleBrandDTO[].class).block().getBody();
        return new ResponseEntity(Arrays.asList(body), HttpStatus.OK);
    }
    @GetMapping(path = "!image")
    public ResponseEntity<List<VehicleCategoryDTO>> getAllVehicleBrandWithOutImage(@RequestHeader(HttpHeaders.AUTHORIZATION) String headers) {
        VehicleBrandDTO[] body = WebClient.create(URL + "/!image").get().headers(h -> h.set(HttpHeaders.AUTHORIZATION, headers)).retrieve().toEntity(VehicleBrandDTO[].class).block().getBody();
        return new ResponseEntity(Arrays.asList(body), HttpStatus.OK);
    }
}
