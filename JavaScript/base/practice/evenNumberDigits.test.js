/**
 * Unit testing the even number digits problem with Jest.
 * @author Andrew Jarombek
 * @since 3/3/2020
 */

const findNumbers = require('./evenNumberDigits');

describe('unit tests', () => {

    it('findNumbers() works as expected', () => {
        expect(findNumbers([12, 345, 2, 6, 7896])).toEqual(2);
    });
});