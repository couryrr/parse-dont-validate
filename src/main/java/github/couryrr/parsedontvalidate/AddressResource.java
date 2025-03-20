package github.couryrr.parsedontvalidate;

import github.couryrr.parsedontvalidate.dto.AddressDto;
import github.couryrr.parsedontvalidate.type.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressResource {
    Map<String, Address> addresses = new HashMap<>();

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto) {
        try {
            Address address = Address.from(addressDto);
            addresses.put(addressDto.occupant(), address);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{occupant}")
    public ResponseEntity<?> getAddress(@PathVariable String occupant) {
        if (addresses.containsKey(occupant)) {
            var address = addresses.get(occupant);
            var response = new AddressDto(occupant,
                    address.getStreet().getValue(),
                    address.getCity().getValue(),
                    address.getState().getValue(),
                    address.getZip().getValue(),
                    address.getUnit().getValue()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
