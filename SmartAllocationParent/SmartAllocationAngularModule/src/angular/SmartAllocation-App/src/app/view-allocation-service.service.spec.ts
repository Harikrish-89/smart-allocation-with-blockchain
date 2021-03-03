import { TestBed, inject } from '@angular/core/testing';

import { ViewAllocationServiceService } from './view-allocation-service.service';

describe('ViewAllocationServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ViewAllocationServiceService]
    });
  });

  it('should be created', inject([ViewAllocationServiceService], (service: ViewAllocationServiceService) => {
    expect(service).toBeTruthy();
  }));
});
