import { TestBed, inject } from '@angular/core/testing';

import { ModifyAllocationServiceService } from './modify-allocation-service.service';

describe('ModifyAllocationServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModifyAllocationServiceService]
    });
  });

  it('should be created', inject([ModifyAllocationServiceService], (service: ModifyAllocationServiceService) => {
    expect(service).toBeTruthy();
  }));
});
