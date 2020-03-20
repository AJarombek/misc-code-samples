class Solution {

    /**
     * Solve the problem using a simple for loop.
     * @param $arr The array that is modified in place.
     * @return The array instance.
     */
    function replaceElements($arr) {
        $maxRight = -1;
        $right = $arr[count($arr) - 1];
        $arr[count($arr) - 1] = -1;

        for ($i = count($arr) - 2; $i >= 0; $i--) {
            $maxRight = max($maxRight, $right);
            $right = $arr[$i];

            $arr[$i] = $maxRight;
        }

        return $arr;
    }
}
