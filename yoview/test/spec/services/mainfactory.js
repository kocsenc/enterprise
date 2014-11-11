'use strict';

describe('Service: MainFactory', function () {

  // load the service's module
  beforeEach(module('paybookApp'));

  // instantiate service
  var MainFactory;
  beforeEach(inject(function (_MainFactory_) {
    MainFactory = _MainFactory_;
  }));

  it('should do something', function () {
    expect(!!MainFactory).toBe(true);
  });

});
